class UserConverter:
    regex = '[A-Z]{3}[0-9]{4}'


    def to_python(self, value):
        return str(value)



    def to_url(self, value):
        return str(value)

