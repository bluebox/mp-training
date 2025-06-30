def outer():
  def inner():
    print("hello")
  outer.last=inner()
  return outer 
a=outer()
outer.naam="kanishka"
print(outer.naam)
