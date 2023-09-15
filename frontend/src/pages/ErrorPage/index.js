import { useRouteError } from 'react-router';

function ErrorPage() {
  const error = useRouteError();
  console.error(error);

  return (
    <div>
      <h1>error</h1>
      <p>{error.statusText || error.message}</p>
    </div>
  );
}

export default ErrorPage;
