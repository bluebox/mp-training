
class Username:
    regex = '[a-z]{2}'

    def to_python(self, value):
        return value

    def to_url(self, value):
        return value
