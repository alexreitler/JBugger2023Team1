// components/common/NotificationPopup.tsx
import React, { useState, useEffect } from "react";
import Notification from "./Notification";
import NotificationProps from "@/app/types/NotificationsProps";

interface NotificationPopupProps {
  onClose: () => void;
}

function NotificationPopup({ onClose }: NotificationPopupProps) {
  let notifications: NotificationProps[] = [];
  useEffect(() => {
    // Fetch notifications data from the JSON file or API
    const fetchData = async () => {
      try {
        const response = await fetch("./notificationsData.json");
        const data = await response.json();
        notifications = data;
      } catch (error) {
        console.error("Error fetching notifications data:", error);
      }
    };

    fetchData();
  }, []); // Empty dependency array to fetch data only once on component mount

  return (
    <div className="notification-popup">
      <button onClick={onClose} className="close-button">
        Close
      </button>
      <div className="notification-list">
        {notifications?.map((notification: NotificationProps) => (
          <Notification
            key={notification.notificationId}
            notificationId={notification.notificationId}
            notificationText={notification.notificationText}
          />
        ))}
      </div>
    </div>
  );
}

export default NotificationPopup;
