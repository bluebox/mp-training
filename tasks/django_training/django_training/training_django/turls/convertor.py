class UserNameConverter:

    regex = 'MED[0-9]{3}'

    def to_python(self,value):
       return value

    def to_url(self,value):
        return value

