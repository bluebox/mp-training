class MyConverter:
    regex = '[a-z]{5}[0-9]{4}'

    def to_python(self, value):
        return value

    def to_url(self, value):
        return value
