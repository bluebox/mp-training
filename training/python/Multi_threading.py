from threading import Thread
import time 
def performance(funky):
    def wrap_func(data,a,b,c,n):
        start_time=time.time()
        sum_val=funky(data,a,b,c,n)
        end_time=time.time()
        return (end_time-start_time)*10000
    return wrap_func

def sum_func(data,start,end,res,index):
    sum_val=0
    for i in range(start,end):
        sum_val+=data[i]
    res[index]=sum_val
@performance
def data_thread_processing(data,a,b,c,n):
    res=[0,0,0,0]
    thread_1=Thread(target=sum_func,args=(data,0,a,res,0))
    thread_2=Thread(target=sum_func,args=(data,a,b,res,1))
    thread_3=Thread(target=sum_func,args=(data,b,c,res,2))
    thread_4=Thread(target=sum_func,args=(data,c,n,res,3))
    thread_1.start()
    thread_2.start()
    thread_3.start()
    thread_4.start()  
    thread_1.join()
    thread_2.join()
    thread_3.join()
    thread_4.join()
    return sum(res)


input_val=[22]*1000000
n=len(input_val)
a,b,c=n//4,n//2,3*n//4

sum_time=0
n_val=1000
for i in range(n_val):
    sum_time+=data_thread_processing(input_val,a,b,c,n)
print(sum_time//n_val)


"""
Due to the GIL's Limitations the process runs only on single thread for CPU bound task and there is context switching and shared resourses
overhead causing this.
"""





