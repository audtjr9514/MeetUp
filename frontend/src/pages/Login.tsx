import LoginForm from '../components/login/LoginForm';
import Image1 from '../assets/background1.png';
import Image2 from '../assets/background2.png';
import Image3 from '../assets/background3.png';
import Image4 from '../assets/background4.png';
import { useState, useEffect, useMemo } from 'react';
import { useNavigate } from 'react-router-dom';

function Login() {
  const navigate = useNavigate();

  // 배경 이미지 랜덤 출력
  // Image[] not working
  const imgArray: any = useMemo(() => [Image1, Image2, Image3, Image4], []);
  const [showImg, setShowImg] = useState();

  useEffect(() => {
    const imgNum: number = Math.round(Math.random() * 3);
    setShowImg(imgArray[imgNum]);
  }, [imgArray]);

  const tokenExpiresIn = Number(localStorage.getItem('tokenExpiresIn'));

  const today = new Date();
  const parsedToday = today.getTime();
  const isExpired = tokenExpiresIn - parsedToday < 0;

  // 로그인 토큰이 만료되지 않았을 때에만 메인 화면으로 이동
  useEffect(() => {
    if (!isExpired) {
      navigate(`/calendar/${localStorage.getItem('id')}`);
    }
  }, [navigate, isExpired]);

  return (
    <div className="h-screen bg-cover" style={{ backgroundImage: `url(${showImg})` }}>
      <div className="bg-title bg-opacity-30 w-screen h-screen flex items-center justify-center">
        <LoginForm />
      </div>
    </div>
  );
}

export default Login;
