import api from './api';

export const dashboardService = {
  getDashboardStats: async () => {
    try {
      const [booksResponse, membersResponse, issuesResponse] = await Promise.all([
        api.get('/books'),
        api.get('/members'),
        api.get('/issues')
      ]);

      const books = booksResponse.data.data || [];x
      const members = membersResponse.data.data || [];
      const issues = issuesResponse.data.data || [];

      const stats = {
        totalBooks: books.length,
        availableBooks: books.filter(book => book.available).length,
        totalMembers: members.filter(member => member.active).length,
        activeIssues: issues.filter(issue => !issue.returned).length,
        overdueBooks: issues.filter(issue => issue.overdue && !issue.returned).length,
        recentTransactions: issues.slice(-5).reverse(),
        popularBooks: getPopularBooks(issues, books),
        memberActivity: getMemberActivity(issues, members)
      };

      return { data: { data: stats } };
    } catch (error) {
      console.error('Dashboard service error:', error);
      throw error;
    }
  }
};

const getPopularBooks = (issues, books) => {
  const bookCounts = {};
  issues.forEach(issue => {
    bookCounts[issue.bookId] = (bookCounts[issue.bookId] || 0) + 1;
  });

  return Object.entries(bookCounts)
    .map(([bookId, count]) => {
      const book = books.find(b => b.bookId === bookId);
      return {
        bookId,
        title: book?.title || 'Unknown Book',
        author: book?.author || 'Unknown Author',
        issueCount: count
      };
    })
    .sort((a, b) => b.issueCount - a.issueCount)
    .slice(0, 5);
};

const getMemberActivity = (issues, members) => {
  const memberCounts = {};
  issues.forEach(issue => {
    memberCounts[issue.memberId] = (memberCounts[issue.memberId] || 0) + 1;
  });

  return Object.entries(memberCounts)
    .map(([memberId, count]) => {
      const member = members.find(m => m.memberId === parseInt(memberId));
      return {
        memberId: parseInt(memberId),
        name: member?.name || 'Unknown Member',
        email: member?.email || '',
        totalIssues: count
      };
    })
    .sort((a, b) => b.totalIssues - a.totalIssues)
    .slice(0, 5);
};
