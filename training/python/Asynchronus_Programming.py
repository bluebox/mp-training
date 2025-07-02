import asyncio
import time 

# async def ans():
#     await asyncio.sleep(1)
#     print("1 second elapsed")
#     await asyncio.sleep(2)
#     print("3 seconds have been elapsed")
#     await asyncio.sleep(3)
#     print("6 seconds have been elapsed")
# async def main_func():
#     await ans()
# asyncio.run(main_func())



# async def fn():
    
#     print("one")
#     await asyncio.sleep(1)
#     await fn2()
#     print('four')
#     await asyncio.sleep(1)
#     print('five')
#     await asyncio.sleep(1)

# async def fn2():
#     await asyncio.sleep(1)
#     print("two")
#     await asyncio.sleep(1)
#     print("three")

# asyncio.run(fn())





'''
Here i'm trying to perform asynchronus and synchronous summation of an iterable to compare the performance and find the optimal split for my pc 
'''
#SYNCHRONUS WAY

print("Synchronus")

def performance(funky):
    def wrap_func(data,n):
        start_time=time.time()
        sum_val=funky(data,n)
        end_time=time.time()
        return (end_time-start_time)*10000
    return wrap_func

@performance
def sync_data_processing(data,n):
    sum_val=0
    for i in range(n):
        sum_val+=data[i]
    return sum_val

input_sync=[22]*1000000
n=len(input_sync)

sum_time=0
n_val=1000
for i in range(n_val):
    sum_time+=sync_data_processing(input_sync,n)
print(sum_time//n_val)



#ASYNCHRONUS WAY 

print("Asynchronus")

async def sum_func(data, start,end):
    sum_val=0
    for i in range(start,end):
        sum_val+=data[i]
    return sum_val
def performance(funky):
    def wrap_func(data,a):
        start_time=time.time()
        sum_val=asyncio.run(funky(data,a))
        end_time=time.time()
        return (end_time-start_time)*10000
    return wrap_func

@performance
async def parallel_data_processing(data,a):
    n1=asyncio.create_task(sum_func(data,0,a))
    n2=asyncio.create_task(sum_func(data,a,-1))

    total_sum= await asyncio.gather(n1,n2)
    return sum(total_sum)

input_val=[22]*1000000
n=len(input_val)
a=n//2

sum_time=0
n_val=1000
for i in range(n_val):
    sum_time+=parallel_data_processing(input_val,a)
print(sum_time//n_val)



