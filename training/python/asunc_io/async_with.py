import asyncio
import aiohttp
async def get_data():
    async with aiohttp.ClientSession() as session:
        async with session.post("http://127.0.0.1:8000/test/hello/",json={"name":"madhav","EmpId":"EmpID123"}) as response:
            return await response.json()
async def get_data2():
    async with aiohttp.ClientSession() as session:
        async with session.get("http://127.0.0.1:8000/test/hello/",params={"name":"madhav","EmpId":"EmpID123"}) as response:
            return await response.json()

async def main():
    results = await asyncio.gather(get_data(),get_data2())
    print(results)
print(asyncio.run(main()))



