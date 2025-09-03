export const toISO = (d) => new Date(d).toISOString().slice(0,10);
export const todayISO = () => toISO(new Date());

export function enumerateDays(startISO, endISO) {
  const out = [];
  let d = new Date(startISO);
  const end = new Date(endISO);
  while (d <= end) {
    out.push(toISO(d));
    d.setDate(d.getDate() + 1);
  }
  return out;
}

export function isWeekend(iso) {
  const day = new Date(iso).getDay(); 
  return day === 0 || day === 6;
}

export function isHoliday(iso, holidays) {
  return holidays.includes(iso);
}

export function workingDaysBetween(startISO, endISO, holidays) {
  return enumerateDays(startISO, endISO).filter(d => !isWeekend(d) && !isHoliday(d, holidays)).length;
}
