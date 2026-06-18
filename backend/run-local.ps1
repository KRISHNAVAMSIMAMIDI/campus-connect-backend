param(
    [string]$DbUrl = "jdbc:mysql://acela.proxy.rlwy.net:25890/railway",
    [string]$DbUsername = "root"
)

$securePassword = Read-Host "DB password" -AsSecureString
$passwordPtr = [Runtime.InteropServices.Marshal]::SecureStringToBSTR($securePassword)

try {
    $env:DB_URL = $DbUrl
    $env:DB_USERNAME = $DbUsername
    $env:DB_PASSWORD = [Runtime.InteropServices.Marshal]::PtrToStringBSTR($passwordPtr)

    .\mvnw.cmd spring-boot:run
}
finally {
    if ($passwordPtr -ne [IntPtr]::Zero) {
        [Runtime.InteropServices.Marshal]::ZeroFreeBSTR($passwordPtr)
    }
    Remove-Item Env:\DB_PASSWORD -ErrorAction SilentlyContinue
}
