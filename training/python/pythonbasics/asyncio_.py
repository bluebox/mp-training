import asyncio

async def func():
    await asyncio.create_task(func2())
    print("1-1")
    await asyncio.sleep(1)
    print("1-2")
    await asyncio.sleep(3)
    print("1-3")

async def func2():
    await asyncio.sleep(2)
    print("2-1")
    await asyncio.sleep(4)
    print("2-2")
    await asyncio.sleep(1)
    print("2-3")
asyncio.run(func())
