import React, { useState, useEffect } from 'react';
import axios from 'axios';

function LoginPage() {
  const [csrfToken, setCsrfToken] = useState('');
  // const [username, setUsername] = useState("jinro1@mail.com");
  const username = 'jinro2@mail.com';
  // const [password, setPassword] = useState("1234");
  const password = '1234';

  useEffect(() => {
    axios
      .get('http://localhost:8080/api/v1/csrf')
      .then((response) => {
        setCsrfToken(response.data);
      })
      .catch((error) => {
        console.log(error);
        alert('Failed to fetch CSRF token');
      });
  }, []);

  const login = () => {
    // const credentails = window.btoa(`${username}:${password}`);
    // const authentication = `Basic ${credentails}`;
    const response = axios.post(
      'http://localhost:8080/login',
      { username, password, _csrf: csrfToken },
      {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          // 'AUTHORIZATION': authentication
        },
        withCredentials: true,
      },
    );
    response.then((result) => {
      if (result.data) {
        console.log('response 출력');
        // window.sessionStorage.setItem(
        //   'Authorization',
        //   result.headers.get('Authorization'),
        // );
        const xsrfToken = result.config.headers['X-XSRF-TOKEN'];
        window.sessionStorage.setItem('XSRF-TOKEN', xsrfToken);
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
