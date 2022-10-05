export interface placesObject{
  id:number,
  place_name:string,
  image:string,
  description:string
}

export interface couponObject{
  id:number,
  couponcode:string,
  discount:number,
  valid_till:Date,
  description:string,
  created_at:Date
}

export interface TourItem {
  id:number,
  tour_name : string,
  tour_from: string,
  tour_to: string,
  tour_type: string,
  nights: number,
  days: number,
  price: number,
  image: string,
  description: string,
  places: placesObject[],
  vehicleid: string,
  coupons: couponObject[],
  created_at:Date,
  updated_at:Date
}
