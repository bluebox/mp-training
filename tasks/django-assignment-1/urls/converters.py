class CustomParameter:
    # must include 2 functionalities and a regex variable

    regex = '[A-Z]{3}[0-9]{6}'

    def to_python(self,value):
        return value

    def to_urls(self,value):
        return value