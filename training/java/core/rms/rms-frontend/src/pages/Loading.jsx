import { useSelector } from "react-redux";

const Loading = () => {

    const loading=useSelector(state=>state.loading);

    return loading ? (
        <div className="plain">
            <img src="/images/loadingAnimation.gif" alt="Loading..." className="loadingGif" />
        </div>
    ) : null;
}

export default Loading;