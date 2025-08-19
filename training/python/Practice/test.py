import asyncio
import aiohttp

async def fetch(session, url):
    async with session.get(url) as response:
        print(f"Fetched from {url} with status {response.status}")
        return await response.text()

async def main():
    urls = ['https://example.com', 'https://httpbin.org/get']
    async with aiohttp.ClientSession() as session:
        tasks = [fetch(session, url) for url in urls]
        await asyncio.gather(*tasks)

asyncio.run(main())
