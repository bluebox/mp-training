class SimpleLogMiddleware:
    def __init__(self, get_response):
        self.get_response = get_response
        print("it is from midleware(self)")

    def __call__(self, request):
        print(f"Request Path: {request.path}")
        response = self.get_response(request)
        print("it is from middleware(__call__),so its working Prasad")
        return response
