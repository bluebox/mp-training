class CustomException(Exception):
    def __init__(self,*args,**kwargs):
        self.args = args
        self.kwargs = kwargs
    def __str__(self):
        return "This is a custom exception "+str(self.args)+str(self.kwargs)

raise CustomException("hello","World",isTrue = True)

