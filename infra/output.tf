# CREDENTIALS FOR AWS SDK - ACCESS TO S3 BUCKET
output "access_key" {
  value     = aws_iam_access_key.app_user_keys.id
  sensitive = true
}

output "secret_key" {
  value     = aws_iam_access_key.app_user_keys.secret
  sensitive = true
}