// In-memory "store" for demo purposes only.
import { isWeekend, isHoliday, enumerateDays, workingDaysBetween, todayISO } from "./utils/date";
import holidays from "./data/Holidays";

let leaves = []; 
let employees = [
  { id: 1, name: "Alice", role: "Developer", leaveBalances: { CL: 3, SL: 5, EL: 10 }, opUsed: 0 },
  { id: 2, name: "Bob", role: "Tester", leaveBalances: { CL: 2, SL: 4, EL: 12 }, opUsed: 1 },
  { id: 3, name: "Charlie", role: "Designer", leaveBalances: { CL: 1, SL: 6, EL: 8 }, opUsed: 0 },
];


let attendances = {}; 


let exitRequests = []; 


let seq = 1;
const nextId = () => seq++;


function countDays(type, start, end) {
  if (type === "SL") {
    return enumerateDays(start, end).length;
  }

  return workingDaysBetween(start, end, holidays);
}


function countOP(before, after) {
  return (before ? 1 : 0) + (after ? 1 : 0);
}

export const api = {
  getHolidays: () => holidays,
      getEmployees: () => employees,

  addEmployee: (emp) => {
    const newEmp = { ...emp, id: nextId(), leaveBalances: { CL: 3, SL: 5, EL: 10 }, opUsed: 0 };
    employees.push(newEmp);
    return newEmp;
  },

  removeEmployee: (id) => {
    employees = employees.filter(e => e.id !== id);
    return employees;
  },
  getEmployees: async () => {
    return [
      { id: 1, name: "Alice Employee", role: "Employee" },
      { id: 2, name: "Bob Employee", role: "Employee" },
    ];
  },

  getManagers: async () => {
    return [
      { id: 1, name: "Charlie Manager", role: "Manager" },
      { id: 2, name: "Diana Manager", role: "Manager" },
    ];
  },

  getAdmins: async () => {
    return [
      { id: 1, name: "Edward Admin", role: "Admin" },
      { id: 2, name: "Fiona Admin", role: "Admin" },
    ];
  },
  getLeaves: (employeeId) => leaves.filter(l => l.employeeId === employeeId),

  getExitRequests: (employeeId) => exitRequests.filter(e => e.employeeId === employeeId),

  getAttendance: () => attendances,

  checkIn: (timeStr) => {
    const d = todayISO();
    if (!attendances[d]) attendances[d] = { checkIn: null, checkOut: null, totalHours: null, autoLeave: false };
    if (attendances[d].autoLeave) {
      throw new Error("Today is auto-marked as leave.");
    }
    if (attendances[d].checkIn) throw new Error("Already checked-in.");
    attendances[d].checkIn = timeStr;
    return attendances[d];
  },

  checkOut: (timeStr) => {
    const d = todayISO();
    if (!attendances[d] || !attendances[d].checkIn) throw new Error("Check-in first.");
    if (attendances[d].checkOut) throw new Error("Already checked-out.");
    attendances[d].checkOut = timeStr;

    const [h1,m1] = attendances[d].checkIn.split(":").map(Number);
    const [h2,m2] = timeStr.split(":").map(Number);
    const mins = (h2*60+m2) - (h1*60+m1);
    attendances[d].totalHours = Math.max(0, Math.round((mins/60)*100)/100);
    return attendances[d];
  },

  applyLeave: ({ employee, baseType, start, end, remarks, includeOPBefore, includeOPAfter, includeSLWithEL }) => {

    if (employee.exit && ["SUBMITTED", "APPROVED"].includes(employee.exit.status)) {
      throw new Error("You have submitted an exit request. Leave application is blocked.");
    }

    if (!remarks?.trim()) throw new Error("Remarks are mandatory.");

  
    const baseDays = countDays(baseType, start, end);


    if (baseType === "CL" && baseDays > 3) {
      throw new Error("CL cannot exceed 3 consecutive working days.");
    }

    if (baseType === "EL" && baseDays > 15) {
      throw new Error("EL cannot exceed 15 working days per request.");
    }

    let segments = [{ type: baseType, start, end }];

   
    if (baseType === "EL" && includeSLWithEL) {

      segments.push({ type: "SL", start, end });
    }


    const opCount = countOP(includeOPBefore, includeOPAfter);
    if (employee.opUsed + opCount > 2) {
      throw new Error(`OP limit exceeded. You have used ${employee.opUsed} of 2.`);
    }
    
    const dayBefore = new Date(start);
    dayBefore.setDate(dayBefore.getDate() - 1);
    const beforeISO = dayBefore.toISOString().slice(0,10);

    const dayAfter = new Date(end);
    dayAfter.setDate(dayAfter.getDate() + 1);
    const afterISO = dayAfter.toISOString().slice(0,10);

    if (includeOPBefore) {
      segments.push({ type: "OP", start: beforeISO, end: beforeISO });
    }
    if (includeOPAfter) {
      segments.push({ type: "OP", start: afterISO, end: afterISO });
    }


    if (baseType === "EL") {

    }
    if (baseType === "CL") {

    }
    if (baseType === "SL") {
  
    }


    const totalsByType = segments.reduce((acc, s) => {
      const days = s.type === "OP" ? 1 : countDays(s.type, s.start, s.end);
      acc[s.type] = (acc[s.type] || 0) + days;
      return acc;
    }, {});

    if ((totalsByType["CL"] || 0) > employee.leaveBalances.CL) throw new Error("Insufficient CL balance.");
    if ((totalsByType["SL"] || 0) > employee.leaveBalances.SL) throw new Error("Insufficient SL balance.");
    if ((totalsByType["EL"] || 0) > employee.leaveBalances.EL) throw new Error("Insufficient EL balance.");

    const totalDays = Object.entries(totalsByType).reduce((n, [,d]) => n + d, 0);

    const leave = {
      id: nextId(),
      employeeId: employee.id,
      segments,
      totalDays,
      status: "APPROVED",
      remarks,
      createdAt: new Date().toISOString(),
    };
    leaves.push(leave);


    employee.leaveBalances = {
      ...employee.leaveBalances,
      CL: employee.leaveBalances.CL - (totalsByType["CL"] || 0),
      SL: employee.leaveBalances.SL - (totalsByType["SL"] || 0),
      EL: employee.leaveBalances.EL - (totalsByType["EL"] || 0),
    };
    employee.opUsed += (totalsByType["OP"] || 0);


    leave.segments.forEach(seg => {
      const days = enumerateDays(seg.start, seg.end);
      days.forEach(d => {
        if (!attendances[d]) attendances[d] = { checkIn: null, checkOut: null, totalHours: null, autoLeave: false };
        attendances[d].autoLeave = true;
      });
    });

    return leave;
  },

  submitExit: ({ employee, intendedDate, remarks }) => {
    if (!remarks?.trim()) throw new Error("Remarks are mandatory.");
    const existing = exitRequests.find(e => e.employeeId === employee.id && e.status !== "APPROVED");
    if (existing) throw new Error("You already have a pending exit request.");

    const exit = {
      id: nextId(),
      employeeId: employee.id,
      intendedDate,
      remarks,
      status: "SUBMITTED",
      createdAt: new Date().toISOString(),
    };
    exitRequests.push(exit);
    employee.exit = { status: "SUBMITTED", date: intendedDate, remarks };

    return exit;
  },
  

  cancelFutureLeavesForExit: (employee) => {
    const today = todayISO();
    const affected = [];
    for (const l of leaves) {
      if (l.employeeId !== employee.id) continue;
      if (l.status !== "APPROVED") continue;

      const future = l.segments.some(s => s.start > today);
      if (future) {
        l.status = "CANCELLED";
        affected.push(l);

        l.segments.forEach(seg => {
          const dcount = seg.type === "OP" ? 1 : countDays(seg.type, seg.start, seg.end);
          if (seg.type === "OP") employee.opUsed = Math.max(0, employee.opUsed - dcount);
          else employee.leaveBalances[seg.type] += dcount;


          enumerateDays(seg.start, seg.end).forEach(d => {
            if (attendances[d]) attendances[d].autoLeave = false;
          });
        });
      }
    }
    return affected;
  }
};
