import React from 'react';
import axios from 'axios';

function LoginPage() {
  const login = () => {
    const response = axios.post(
      'http://localhost:8080/user',
      { username: 'jinro1@mail.com', password: '1234' },
      {
        withCredentials: true,
        // Authorization : `Basic ${window.btoa('jinro1@mail.com:1234')}`
      },
    );
    response.then((result) => {
      if (result.data) {
        window.sessionStorage.setItem(
          'Authorization',
          result.headers.get('Authorization'),
        );
        alert('로그인 완료');
        window.location.href = 'http://localhost:8080/';
        // alert(window.sessionStorage.getItem("Authorization"));
      } else {
        alert('로그인에 실패했습니다.');
      }
    });
  };

  return (
    <div>
      로그인 페이지입니다.
      <button type="submit" onClick={login}>
        로그인
      </button>
    </div>
  );
}

export default LoginPage;
