import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import githubLogo from './github-logo.png'; 
import googleLogo from './google-logo.png';  

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/api/auth/login", { email, password });
      console.log(response.data);  // Successful login response
      navigate("/dashboard");  // Redirect to dashboard
    } catch (err) {
      setError("Invalid email or password");
    }
  };

  const handleGithubLogin = () => {
    window.location.href = "http://localhost:8080/oauth2/authorization/github";  // Redirect to GitHub OAuth2 login
  };

  const handleGoogleLogin = () => {
    window.location.href = "http://localhost:8080/oauth2/authorization/google";  // Redirect to Google OAuth2
  };

  return (
    <div className="login-form">
      <h2>Login</h2>
      {error && <p className="error">{error}</p>}
      <form onSubmit={handleLogin}>
        <div>
          <label>Email:</label>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>
        <div>
          <label>Password:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <button type="submit">Login</button>
      </form>
      
      <div className="oauth-login">
        <p>Or login with:</p>
        <button onClick={handleGithubLogin} className="github-login">
          <img src={githubLogo} alt="GitHub Logo" className="github-logo" /> Login with GitHub
        </button>
        <button onClick={handleGoogleLogin} className="google-login">
          <img src={googleLogo} alt="Google Logo" className="github-logo" /> Login with Google
        </button>
      </div>
    </div>
  );
}

export default Login;
