from contextlib import contextmanager


class open_file():
    def __init__(self, filename, mode):
        self.filename = filename
        self.mode = mode

    def __enter__(self):
        self.file = open(self.filename, self.mode)
        return self.file

    def __exit__(self, exc_type, exc_val, exc_tb):
        self.file.close()


with open_file("test.txt", 'r') as file:
    print(file.read())

print(file.closed)


# this is manual creation of context managers
# below is the example of context manager using function

@contextmanager
def open_file_func(filename, mode):
    f = open(filename, mode)
    yield f
    f.close()


with open_file_func('test.txt', 'r') as f:
    print(f.read())

print(f.closed)
