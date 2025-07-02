import asyncio


async def wait(delay):
    print("in wait")
    await asyncio.sleep(delay)
    print("data fetched")
    return {"data": "data is received"}


async def main():
    result = await wait(2)
    print(result)


asyncio.run(main())
print("anand")

# _________________________create_task
async def task1():
    print("Task 1 started")
    await asyncio.sleep(2)
    print("Task 1 finished")
    return "task1"



async def task2():
    print("Task 2 started")
    await asyncio.sleep(1)
    print("Task 2 finished")
    return "task2"


async def main():
    t1 = asyncio.create_task(task1())
    t2 = asyncio.create_task(task2())

    print("Both tasks created, now waiting...")

    task=await t1
    task3=await t2
    print(task)
    print(task3)

asyncio.run(main())

# ______________________________________asyncio.gather(task1,task2)

print("anand")
async def task1():
    await asyncio.sleep(2)
    print(10/0)
    return "Task 1 result"

async def task2():
    await asyncio.sleep(1)
    return "Task 2 result"

async def main():
    results = await asyncio.gather(task1(), task2(),return_exceptions=True)
    print(results)

asyncio.run(main())
#______________________________________aysncio.TaskGroup this automatically cancel if one task fails or throw error
# unlike .gather it run all task even it contains a error in it
