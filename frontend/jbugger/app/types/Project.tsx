interface Project {
  id: string;
  name: string;
  admin: string;
  projectManager: string;
  testManager: string;
  openTickets: number;
  totalMembers: number;
  deadline: string;
}

export default Project;