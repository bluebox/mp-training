import react from '@vitejs/plugin-react'
import { defineConfig } from 'vite'

export default defineConfig({
    plugins: [react()],
    server: {
        port: "3000",
    },
    base: '/marketing/',
    build: {
        sourcemap: true,
        minify:"terser"
    },
    assetsInclude: ['**/*.xls'],
})

