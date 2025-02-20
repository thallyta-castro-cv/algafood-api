resource "aws_s3_bucket" "algafood_prod_images" {
  bucket = "algafood-prod-images"
}

resource "aws_s3_object" "catalog_folder" {
  bucket       = aws_s3_bucket.algafood_prod_images.id
  key          = "catalog/" 
  content      = ""
  content_type = "application/x-directory"
}

resource "aws_s3_bucket_public_access_block" "block_public_access" {
  bucket                  = aws_s3_bucket.algafood_prod_images.id
  block_public_acls       = false
  block_public_policy     = false
  restrict_public_buckets = false
}

resource "aws_s3_bucket_ownership_controls" "ownership" {
  bucket = aws_s3_bucket.algafood_prod_images.id
  rule {
    object_ownership = "BucketOwnerEnforced"
  }
}

resource "aws_s3_bucket_policy" "public_access" {
  bucket = aws_s3_bucket.algafood_prod_images.id
  policy = <<POLICY
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Principal": "*",
      "Action": "s3:GetObject",
      "Resource": "arn:aws:s3:::algafood-prod-images/*"
    }
  ]
}
POLICY
  depends_on = [aws_s3_bucket_public_access_block.block_public_access]
}

### iam.tf

resource "aws_iam_user" "app_user" {
  name = "algafood-app-user"
}

resource "aws_iam_policy" "s3_access_policy" {
  name        = "algafood-s3-policy"
  description = "Policy to allow app to access S3 bucket"
  policy      = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": [
        "s3:PutObject",
        "s3:DeleteObject"
      ],
      "Resource": "arn:aws:s3:::algafood-prod-images/*"
    }
  ]
}
EOF
}

resource "aws_iam_user_policy_attachment" "attach_policy" {
  user       = aws_iam_user.app_user.name
  policy_arn = aws_iam_policy.s3_access_policy.arn
}

resource "aws_iam_access_key" "app_user_keys" {
  user = aws_iam_user.app_user.name
}
