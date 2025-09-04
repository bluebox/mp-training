import api from './api';

export const reportService = {
  getCirculationReport: async (startDate, endDate) => {
    try {
      const [issuesResponse, membersResponse, booksResponse] = await Promise.all([
        api.get('/issues'),
        api.get('/members'),
        api.get('/books')
      ]);

      const issues = issuesResponse.data.data || [];
      const members = membersResponse.data.data || [];
      const books = booksResponse.data.data || [];

      const filteredIssues = issues.filter(issue => {
        const issueDate = new Date(issue.issueDate);
        const start = new Date(startDate);
        const end = new Date(endDate);
        return issueDate >= start && issueDate <= end;
      });

      const enrichedTransactions = filteredIssues.map(issue => {
        const member = members.find(m => m.memberId === issue.memberId) || {};
        const book = books.find(b => b.bookId === issue.bookId) || {};
        
        return {
          issueId: issue.issueId,
          transactionDate: issue.issueDate,
          
          memberId: `M${issue.memberId.toString().padStart(4, '0')}`,
          memberName: member.name || 'Unknown Member',
          memberEmail: member.email || 'N/A',
          memberPhone: member.mobile || 'N/A',
          memberStatus: member.status || 'N/A',
          
          bookId: issue.bookId,
          bookTitle: book.title || 'Unknown Book',
          bookAuthor: book.author || 'Unknown Author',
          bookCategory: book.category || 'N/A',
          
          issueDate: issue.issueDate,
          expectedReturnDate: issue.returnDate,
          actualReturnDate: issue.actualReturnDate || 'Not Returned',
          transactionStatus: issue.returned ? 'RETURNED' : 'ACTIVE',
          
          isOverdue: issue.overdue ? 'YES' : 'NO',
          daysOverdue: issue.daysOverdue || 0,
          
          returnedOnTime: issue.returned ? 
            (new Date(issue.actualReturnDate) <= new Date(issue.returnDate) ? 'YES' : 'NO') : 'N/A',
          totalDaysIssued: issue.returned ? 
            Math.ceil((new Date(issue.actualReturnDate) - new Date(issue.issueDate)) / (1000 * 60 * 60 * 24)) : 
            Math.ceil((new Date() - new Date(issue.issueDate)) / (1000 * 60 * 60 * 24))
        };
      }).sort((a, b) => new Date(b.transactionDate) - new Date(a.transactionDate));

      return {
        data: {
          totalIssued: filteredIssues.length,
          totalReturned: filteredIssues.filter(issue => issue.returned).length,
          stillActive: filteredIssues.filter(issue => !issue.returned).length,
          overdueCount: filteredIssues.filter(issue => issue.overdue && !issue.returned).length,
          transactions: enrichedTransactions,
          summary: {
            dateRange: `${startDate} to ${endDate}`,
            averageDaysIssued: enrichedTransactions.length > 0 ? 
              Math.round(enrichedTransactions.reduce((sum, t) => sum + t.totalDaysIssued, 0) / enrichedTransactions.length) : 0,
            onTimeReturnRate: enrichedTransactions.filter(t => t.returnedOnTime === 'YES').length + '/' + enrichedTransactions.filter(t => t.transactionStatus === 'RETURNED').length
          }
        }
      };
    } catch (error) {
      throw error;
    }
  },

  getMemberActivityReport: async () => {
    try {
      const [membersResponse, issuesResponse] = await Promise.all([
        api.get('/members'),
        api.get('/issues')
      ]);

      const members = membersResponse.data.data || [];
      const issues = issuesResponse.data.data || [];

      const memberStats = members.map(member => {
        const memberIssues = issues.filter(issue => issue.memberId === member.memberId);
        const activeIssues = memberIssues.filter(issue => !issue.returned);
        const completedIssues = memberIssues.filter(issue => issue.returned);
        const overdueIssues = memberIssues.filter(issue => issue.overdue && !issue.returned);

        return {
          memberId: member.memberId,
          memberName: member.name,
          memberEmail: member.email,
          memberPhone: member.mobile || 'N/A',
          memberGender: member.gender || 'N/A',
          memberStatus: member.status,
          memberAddress: member.address || 'N/A',
          
          totalBooksIssued: memberIssues.length,
          currentlyIssued: activeIssues.length,
          booksReturned: completedIssues.length,
          overdueBooks: overdueIssues.length,
          status: member.status || 'ACTIVE',
          
          membershipEfficiency: memberIssues.length > 0 ? 
            `${Math.round((completedIssues.length / memberIssues.length) * 100)}%` : '0%',
          averageIssueLength: completedIssues.length > 0 ?
            Math.round(completedIssues.reduce((sum, issue) => {
              return sum + Math.ceil((new Date(issue.actualReturnDate) - new Date(issue.issueDate)) / (1000 * 60 * 60 * 24));
            }, 0) / completedIssues.length) + ' days' : 'N/A',
          
          lastIssueDate: memberIssues.length > 0 ? 
            memberIssues.sort((a, b) => new Date(b.issueDate) - new Date(a.issueDate))[0].issueDate : 'Never',
          memberCategory: reportService.categorizeMember(memberIssues.length, overdueIssues.length)
        };
      }).sort((a, b) => b.totalBooksIssued - a.totalBooksIssued);

      return { data: { data: memberStats } };
    } catch (error) {
      throw error;
    }
  },

  getBookPopularityReport: async () => {
    try {
      const [booksResponse, issuesResponse] = await Promise.all([
        api.get('/books'),
        api.get('/issues')
      ]);

      const books = booksResponse.data.data || [];
      const issues = issuesResponse.data.data || [];

      const bookStats = books.map(book => {
        const bookIssues = issues.filter(issue => issue.bookId === book.bookId);
        const activeIssues = bookIssues.filter(issue => !issue.returned);

        return {
          bookId: book.bookId,
          title: book.title,
          author: book.author,
          category: book.category,
          status: book.status,
          availability: book.availability,
          available: book.availability === 'A',
          totalIssues: bookIssues.length,
          currentlyIssued: activeIssues.length,
          lastIssuedDate: bookIssues.length > 0 ? 
            bookIssues.sort((a, b) => new Date(b.issueDate) - new Date(a.issueDate))[0].issueDate : 'Never',
          popularity: bookIssues.length > 10 ? 'High' : bookIssues.length > 5 ? 'Medium' : bookIssues.length > 0 ? 'Low' : 'Unused'
        };
      }).sort((a, b) => b.totalIssues - a.totalIssues);

      return { data: { data: bookStats } };
    } catch (error) {
      throw error;
    }
  },

  getOverdueReport: async () => {
    try {
      const [issuesResponse, membersResponse, booksResponse] = await Promise.all([
        api.get('/issues'),
        api.get('/members'),
        api.get('/books')
      ]);

      const issues = issuesResponse.data.data || [];
      const members = membersResponse.data.data || [];
      const books = booksResponse.data.data || [];

      const overdueIssues = issues.filter(issue => issue.overdue && !issue.returned);

      const overdueData = overdueIssues.map(issue => {
        const member = members.find(m => m.memberId === issue.memberId) || {};
        const book = books.find(b => b.bookId === issue.bookId) || {};

        const daysOverdue = Math.ceil((new Date() - new Date(issue.returnDate)) / (1000 * 60 * 60 * 24));

        return {
          issueId: issue.issueId,
          bookId: issue.bookId,
          bookTitle: book.title || 'Unknown Book',
          bookAuthor: book.author || 'Unknown Author',
          memberId: issue.memberId,
          memberName: member.name || 'Unknown Member',
          memberEmail: member.email || 'unknown@example.com',
          memberMobile: member.mobile || 'N/A',
          issueDate: issue.issueDate,
          returnDate: issue.returnDate,
          daysOverdue: daysOverdue > 0 ? daysOverdue : 1,
          severity: daysOverdue > 14 ? 'Critical' : daysOverdue > 7 ? 'High' : 'Medium'
        };
      }).sort((a, b) => b.daysOverdue - a.daysOverdue);

      return { data: { data: overdueData } };
    } catch (error) {
      throw error;
    }
  },

  categorizeMember: (totalIssues, overdueCount) => {
    if (overdueCount > 2) return 'High Risk';
    if (totalIssues > 10) return 'Active Reader';
    if (totalIssues > 5) return 'Regular User';
    if (totalIssues > 0) return 'Occasional User';
    return 'New Member';
  },

  exportToCSV: (data, filename) => {
    if (!data || data.length === 0) {
      alert('No data available to export');
      return;
    }

    const headers = Object.keys(data[0]).map(header => {
      return header.replace(/([A-Z])/g, ' $1')
                  .replace(/^./, str => str.toUpperCase())
                  .replace(/Id/g, 'ID');
    });

    const csvContent = [
      headers.join(','),
      ...data.map(row => 
        Object.values(row).map(value => {
          if (value === null || value === undefined) return '""';
          if (typeof value === 'string' && value.includes(',')) return `"${value}"`;
          if (typeof value === 'boolean') return value ? 'YES' : 'NO';
          return `"${value}"`;
        }).join(',')
      )
    ].join('\n');

    const BOM = '\uFEFF';
    const blob = new Blob([BOM + csvContent], { type: 'text/csv;charset=utf-8;' });
    
    const link = document.createElement('a');
    const url = URL.createObjectURL(blob);
    link.setAttribute('href', url);
    
    const timestamp = new Date().toISOString().slice(0, 19).replace(/:/g, '-');
    link.setAttribute('download', `${filename}_${timestamp}.csv`);
    
    link.style.visibility = 'hidden';
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
    
    URL.revokeObjectURL(url);
    
    alert(`Report exported successfully as ${filename}_${timestamp}.csv`);
  }
};
