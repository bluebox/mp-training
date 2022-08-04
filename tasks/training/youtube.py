import tkinter as tk
from pytube import YouTube

  


def video_downloader(url):
    
    my_video = YouTube(url)





    my_video = my_video.streams.get_highest_resolution()


    my_video.download()
  
def printInput():
    inp = inputtxt.get(1.0, "end-1c")
    video_downloader(inp)

    lbl.config(text = "download succesfully")
  
frame = tk.Tk()

frame.geometry('400x200')

inputtxt = tk.Text(frame,
                   height = 5,
                   width = 20)
  
inputtxt.pack()

printButton = tk.Button(frame,
                        text = "Print", 
                        command = printInput)
printButton.pack()
  

lbl = tk.Label(frame, text = "sdgh")
lbl.pack()
frame.mainloop()