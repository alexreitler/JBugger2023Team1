import type { Config } from 'tailwindcss'

const config: Config = {
  content: [
    './pages/**/*.{js,ts,jsx,tsx,mdx}',
    './components/**/*.{js,ts,jsx,tsx,mdx}',
    './app/**/*.{js,ts,jsx,tsx,mdx}',
  ],
  theme: {
    extend: {
      colors: {
        "msg-magenta": "#8A1B4D",
        "light-gray": "#FCFAFA",
        "medium-gray": "#D9D9D9",
        "dark-gray": "#7F7F7F",

      },
      
    },
  },
  plugins: [],
}
export default config
