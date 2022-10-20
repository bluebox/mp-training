from django.core.paginator import Paginator
def listing(request, object_list):
    paginator = Paginator(object_list, 4)
    page_number = int(request.GET.get('page'))
    if not page_number:
        page_number = 1
    if page_number > paginator.num_pages:
        page_number = paginator.num_pages
    elif page_number < 1:
        page_number = 1
    page_obj = paginator.get_page(page_number)
    return page_obj, paginator.num_pages, page_number
