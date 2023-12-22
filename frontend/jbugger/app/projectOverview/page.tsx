import React from "react";

import Project from "../types/Project";

const sampleProject: Project = {
  id: "1",
  name: "Sample Project",
  admin: "John Doe",
  projectManager: "Alice Smith",
  testManager: "Bob Johnson",
  openTickets: 5,
  totalMembers: 10,
  deadline: "2023-12-31",
};

const ProjectOverview = () => {
  return (
    <div className="text-msg-magenta container mx-auto mt-8 p-4">
      <h1 className=" text-5xl font-bold mb-24">Project Overview</h1>
      <div className="text-3xl">
        <div className="mb-14">
          <span className="font-semibold">Name:</span> {sampleProject.name}
        </div>
        <div className="mb-14">
          <span className="font-semibold">Admin:</span> {sampleProject.admin}
        </div>
        <div className="mb-14">
          <span className="font-semibold">Project Manager:</span>{" "}
          {sampleProject.projectManager}
        </div>
        <div className="mb-14">
          <span className="font-semibold">Test Manager:</span>{" "}
          {sampleProject.testManager}
        </div>
        <div className="mb-14">
          <span className="font-semibold">Open Tickets:</span>{" "}
          {sampleProject.openTickets}
        </div>
        <div className="mb-14">
          <span className="font-semibold">Total Members:</span>{" "}
          {sampleProject.totalMembers}
        </div>
        <div className="mb-14">
          <span className="font-semibold">Deadline:</span>{" "}
          {sampleProject.deadline}
        </div>
      </div>
    </div>
  );
};

export default ProjectOverview;
