import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  preview: {
    port: 5173,
  },
  build: {
    terserOptions: {
      compress: {
        drop_console: true,
      },
    },
    minify: "terser",
    outDir: "dist",
  },
});
