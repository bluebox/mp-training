class UserName:
    regex='[A-H]{3}[0-6]{3}[a-z]{3}'

    def to_python(self, value):
        return value

    def to_url(self, value):
        return value