import React, { useState, useEffect } from 'react';
import axios from 'axios';

function LoginPage() {
  const [csrfToken, setCsrfToken] = useState('');

  useEffect(() => {
    axios
      .get('http://localhost:8080/api/v1/csrf')
      .then((response) => {
        // setCsrfToken(response.data.csrfToken);
        // alert(csrfToken);
        setCsrfToken(response.data);
      })
      .catch((error) => {
        console.log(error);
        alert('Failed to fetch CSRF token');
      });
  }, []);

  const login = () => {
    const response = axios.post(
      'http://localhost:8080/login',
      { username: 'jinro1@mail.com', password: '1234', _csrf: csrfToken },
      {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
        withCredentials: true,
      },
    );
    response.then((result) => {
      if (result.data) {
        window.sessionStorage.setItem(
          'Authorization',
          result.headers.get('Authorization'),
        );
        console.log(result);
        window.location.href = 'http://localhost:8080/?continue';
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
