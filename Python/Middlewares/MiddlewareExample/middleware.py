from django.http import HttpResponse


def CustomFunctionMiddleware(get_response):
    def middleware(request):
        print("before the view")
        response = get_response(request)
        print("after the view")
        # return HttpResponse('jai shree ram')
        # the above line of code break here and the request can't reach the view
        # we mainly use the Custom middleware to protect the page that is only the login user can access the view
        # if request.user.isAuthenticated==False:
        #     return HttpResponse('login first to access this page')
        return response

    return middleware


class CustomClassMiddleware:
    def __init__(self, get_response):
        self.get_response = get_response

    def __call__(self, request):
        print('before view')
        response = self.get_response(request)
        print('after_view')
        return response

    def process_view(self, request, view_func, view_args, view_kwargs):
        # this hook is just executed before the view
        print(request.path)
        print(request.method)
        print(request.user)
        print('In process_view middleware')
        print(f' View Function: {view_func.__name__}')
        print(f' Positional Args: {view_args}')
        print(f' Keyword Args: {view_kwargs}')
        return None

    def process_exception(self,request,exception):
        # this happens when view raise a exception then this is handled
        print(str(exception))
        return HttpResponse(f'something went wrong {str(exception)}')

    def process_template_response(self,request,response):
        # this Executes after the view and before the TemplateResponse
        response.context_data['message']="jai shree ram"
        return response