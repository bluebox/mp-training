import asyncio

async def func():
    print("In a async function")
    await asyncio.sleep(10)
    print("Exiting async function")
async def func1():
    await asyncio.sleep(50)
async def func2():
    a = input("Enter a number")
    if a == "1":
        await func()
    else:
        return
    await func2()
async def main():
    await asyncio.gather(func1(),func2())
if __name__ == "__main__":
    asyncio.run(main())