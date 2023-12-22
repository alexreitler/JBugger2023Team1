// components/common/Notification.tsx
import React, { useState } from "react";

interface NotificationProps {
  notificationId: string;
  notificationText: string;
}

function Notification({ notificationId, notificationText }: NotificationProps) {
  const [isDismissed, setIsDismissed] = useState(false);

  const handleDismiss = () => {
    setIsDismissed(true);
  };

  return (
    <div className={`notification ${isDismissed ? "dismissed" : ""}`}>
      <p>{notificationText}</p>
      {!isDismissed && (
        <button onClick={handleDismiss} className="dismiss-button">
          Dismiss
        </button>
      )}
    </div>
  );
}

export default Notification;
