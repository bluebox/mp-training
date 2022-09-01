class custompara:
    regex = '[a-z]{1}[A-Z]{1}[0-9]{3}'
    def to_python(self,value):
        return value
    def to_urls(self,value):
        return value