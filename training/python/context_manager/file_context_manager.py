class FileContextManager:
    def __init__(self,file_name,file_mode,file=None):
        self.file_name = file_name
        self.file_mode = file_mode
        self.file = file
    def __enter__(self):
        self.file = open(self.file_name,self.file_mode)
        return self.file
    def __exit__(self, exc_type, exc_val, exc_tb):
        self.file.close()


with FileContextManager('text.txt','r') as f:
    print(f.read())

for i in range(100):
    with FileContextManager('text.txt','r') as f:
        print(f.read())
