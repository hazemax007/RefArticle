export interface LoginResponse {
    authenticationToken: string;
    refreshToken: string;
    expiresAt: any;
    username: string;
}