import { readFileSync, writeFileSync } from 'fs';

interface CommitInfo {
  sha: string;
  message: string;
  repository: string;
  branch: string;
}

interface History {
  commit: CommitInfo;
}

async function fetchLatestSha(owner: string, repo: string, branch: string): Promise<string> {
  const url = `https://api.github.com/repos/${owner}/${repo}/branches/${branch}`;
  const response = await fetch(url);
  if (!response.ok) {
    throw new Error(`Failed to fetch latest SHA: ${response.statusText}`);
  }
  const data = await response.json();
  return data.commit.sha;
}

async function fetchCommits(owner: string, repo: string, sinceSha: string, untilSha: string): Promise<any[]> {
  const url = `https://api.github.com/repos/${owner}/${repo}/compare/${sinceSha}...${untilSha}`;
  const response = await fetch(url);
  if (!response.ok) {
    throw new Error(`Failed to fetch commits: ${response.statusText}`);
  }
  const data = await response.json();
  return data.commits || [];
}

function formatChangelog(commits: any[]): string {
  let changelog = '# ✨ Changelog\n\n';
  commits.forEach(commit => {
    const sha = commit.sha;
    let message = commit.commit.message.split('\n')[0]; // First line of commit message
    // Mask #number to links
    message = message.replace(/#(\d+)/g, '[#$1](https://github.com/koiverse/ArchiveTune/issues/$1)');
    const author = commit.commit.author.name;
    const date = new Date(commit.commit.author.date).toISOString().split('T')[0];
    changelog += `- [\`${sha.slice(0, 7)}\`](https://github.com/koiverse/ArchiveTune/commit/${sha}) - **${message}** by **@${author}**\n`;
  });
  return changelog;
}

async function main() {
  try {
    // Read history/commit.json
    const historyData = readFileSync('history/commit.json', 'utf-8');
    const history: History = JSON.parse(historyData);
    const lastSha = history.commit.sha;
    const repo = history.commit.repository; // e.g., "koiverse/ArchiveTune"
    const [owner, repoName] = repo.split('/');
    const branch = history.commit.branch; // e.g., "dev"

    // Get the latest SHA from dev branch
    const latestSha = await fetchLatestSha(owner, repoName, branch);

    console.log(`Comparing from ${lastSha} to ${latestSha}`);

    // Fetch commits
    const commits = await fetchCommits(owner, repoName, lastSha, latestSha);

    // Generate changelog
    const changelog = formatChangelog(commits);

    // Write to changelog.md
    writeFileSync('changelog.md', changelog);
    console.log('Changelog generated: changelog.md');
  } catch (error) {
    console.error('Error generating changelog:', error);
  }
}

main();