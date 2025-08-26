import { useLocation, useNavigate, useParams } from "react-router-dom";

export const withNavigation = (Component) => {
  function ComponentWithNavigation(props) {
    const location = useLocation();
    const navigate = useNavigate();
    const params = useParams();
    return <Component {...props} navigation={{ location, navigate, params }} />;
  }
  return ComponentWithNavigation;
};
