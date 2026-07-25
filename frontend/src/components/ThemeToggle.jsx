import { useTheme } from "../ThemeContext.jsx";

function ThemeToggle({ className = "" }) {
  const { theme, toggleTheme } = useTheme();
  const isDark = theme === "dark";

  return (
    <button
      type="button"
      className={`theme-toggle ${className}`}
      onClick={toggleTheme}
      title={isDark ? "Switch to light mode" : "Switch to dark mode"}
    >
      {isDark ? "☀️ Light" : "🌙 Dark"}
    </button>
  );
}

export default ThemeToggle;
