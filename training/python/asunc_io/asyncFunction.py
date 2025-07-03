import asyncio

async def func():
    print("In a async function")
    await asyncio.sleep(10)
    print("Exiting async function")
asyncio.run(func())