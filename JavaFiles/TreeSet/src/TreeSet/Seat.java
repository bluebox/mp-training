package TreeSet;


class Seat implements Comparable<Seat> {
	private int SeatNo;
	private boolean SeatStatus;
	
	public Seat() {
		super();
	}

	public Seat(int seatNo, boolean seatStatus) {
		SeatNo = seatNo;
		SeatStatus = seatStatus;
	}

	public int getSeatNo() {
		return SeatNo;
	}
	
	public void setSeatNo(int seatNo) {
		SeatNo = seatNo;
	}
	public boolean isSeatStatus() {
		return SeatStatus;
	}
	public void setSeatStatus(boolean seatStatus) {
		SeatStatus = seatStatus;
	}
	
  @Override
    public int compareTo(Seat other) {
        return Integer.compare(this.SeatNo, other.SeatNo);
    }
	

  @Override
  public String toString() {
//	return "Seat [SeatNo=" + SeatNo + ", SeatStatus=" + SeatStatus + "]";
		return  SeatStatus ? " ".repeat(4-String.valueOf(SeatNo).length())+String.valueOf(SeatNo) + "*" : " ".repeat(5-String.valueOf(SeatNo).length())+String.valueOf(SeatNo);

  }


}


