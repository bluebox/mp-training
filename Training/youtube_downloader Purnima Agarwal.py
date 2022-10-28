from tkinter import * 
from pytube import YouTube 

root = Tk()
root.geometry('500x300') 
root.resizable(0, 0) 
root.title('Purnima')

# downlaod

def download():
    url = YouTube(str(link.get()))
    mp4 = url.streams.filter(file_extension="mp4")
    mp4_v = mp4.get_by_resolution("360p")
    mp4_v.download()


    # video = url.streams.first() 
    # video.download() 
    Label(root, text="Downloaded", font="arial 15").place(x=100, y=120)


# Label 

Label(root, text="YouTube Video Downloader", font='san-serif 14 bold').pack()
link = StringVar()
Label(root, text="Paste your link here", font='san-serif 15 bold').place(x=150, y=55)
link_enter = Entry(root, width=70, textvariable=link).place(x=30, y=85)

#button

Button(root, text='Download', font='san-serif 16 bold', padx=2,command=download).place(x=140, y=150)


root.mainloop()
