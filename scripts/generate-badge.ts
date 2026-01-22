import fs from 'fs';
import path from 'path';

async function getLastBuildTime(): Promise<string> {
  const url = 'https://api.github.com/repos/sang765/ArchiveTune-Nightly/actions/runs?per_page=10';
  const response = await fetch(url);
  if (!response.ok) {
    throw new Error(`Failed to fetch: ${response.status}`);
  }
  const data = await response.json();
  const runs = data.workflow_runs || [];
  const nightlyRun = runs.find((run: any) => run.name === 'Nightly Build' && run.conclusion === 'success');
  if (!nightlyRun) {
    throw new Error('No successful Nightly Build run found');
  }
  const date = new Date(nightlyRun.created_at);
  const year = date.getUTCFullYear();
  const month = String(date.getUTCMonth() + 1).padStart(2, '0');
  const day = String(date.getUTCDate()).padStart(2, '0');
  const hours = String(date.getUTCHours()).padStart(2, '0');
  const minutes = String(date.getUTCMinutes()).padStart(2, '0');
  const seconds = String(date.getUTCSeconds()).padStart(2, '0');
  return `${year}/${month}/${day} ${hours}:${minutes}:${seconds}`;
}

async function generateBadge() {
  try {
    const buildTime = await getLastBuildTime();
    const text = `Build: ${buildTime}`;

    // Calculate widths more accurately
    const iconWidth = 40;
    const gap = 8;
    const groupPadding = 12;
    const textWidth = text.length * 15;
    const groupWidth = iconWidth + gap + textWidth;
    const height = groupPadding * 2 + 40;
    const width = groupPadding * 2 + groupWidth;

    const svg = `<svg width="${width}" height="${height}" xmlns="http://www.w3.org/2000/svg">
      <rect x="0" y="0" width="${width}" height="${height}" rx="30" ry="30" fill="#A5D6A7"/>
      <g transform="translate(${groupPadding}, ${groupPadding})">
        <circle cx="20" cy="20" r="20" fill="none" stroke="#2E7D32" stroke-width="3"/>
        <path d="M20 6.67v13.33l5 3" stroke="#2E7D32" stroke-width="3" stroke-linecap="round" stroke-linejoin="round" fill="none"/>
        <text x="${iconWidth + gap}" y="30" font-family="Roboto" font-size="30" font-weight="900" fill="#2E7D32">${text}</text>
      </g>
    </svg>`;

    const outputPath = path.join('images', 'badges', 'last-nightly-build.svg');
    fs.writeFileSync(outputPath, svg);
    console.log(`Badge generated at ${outputPath}`);
  } catch (error) {
    console.error('Error generating badge:', error);
  }
}

generateBadge();