import React from "react";
import Link from "next/link";
import Image from "next/image";
import NotificationsPopup from "../common/NotificationPopup";

//TODO: manage user login state

const Navbar = () => {
  //const [isNotificationsOpen, setIsNotificationsOpen] = useState(false);
  //const [isClient, setIsClient] = useState(false);

  /*useEffect(()=>{
    setIsClient(true);
  }, [])

  const handleNotificationsClick = () => {
    //setIsNotificationsOpen(!isNotificationsOpen);
  };

  const handleProfileClick = () => {
    // Handle profile click
  };*/

  return (
    <nav className="bg-light-gray fixed top-0 w-full">
      <div className="w-full h-20 sticky top-0">
        <div className="container mx-auto px-4 h-full">
          <div className="flex justify-between items-center h-full">
            <Image src="/LOGO.png" alt="Logo" width={250} height={250} />
            <ul className="hidden md:flex gap-x-6 text-dark-gray text-2xl">
              <li>
                <Link href="/projectOverview">
                  <p>Project Details</p>
                </Link>
              </li>
              <li>
                <Link href="/tasks">
                  <p>Dashboard</p>
                </Link>
              </li>
              <li>
                <Link href="/admin">
                  <p>Admin</p>
                </Link>
              </li>
            </ul>
            <div>
              <Link href={"/userDetails"}>
                <Image
                  src="/profile.png"
                  alt="Notifications"
                  width={20}
                  height={20}
                />
              </Link>
              {/*<Image src="/notification.png" alt="Profile" width={20} height={20} />*/}
              {/*TODO: make into components*/}
            </div>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
