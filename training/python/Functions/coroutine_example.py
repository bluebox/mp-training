# def coroutine():
#     print("coroutine started")
#     value = yield
#     print("Received value :",value)
#
# coro = coroutine()
# next(coro)
# coro.send("hello")

def sinc():
    try:
        while(True):
            value = yield
            print("caught : ",value)
    except GeneratorExit:
        print("Buffer coroutine closed")



def filter_coroutine(word = "error"):
    try:
        buffer = sinc()
        next(buffer)
        while(True):
            value = yield
            if word in value:
                print(f"{value}")
                buffer.send(value)
    except GeneratorExit:
        print("filter coroutine closed")
        buffer.close()
coro = filter_coroutine()
next(coro)
coro.send("log error")
coro.send("warning at line 1")
coro.send("error at line 5")
coro.close()

