package myproject;

import com.pulumi.Context;
import com.pulumi.Pulumi;
import com.pulumi.aws.s3.Bucket;
import com.pulumi.aws.s3.BucketArgs;
import com.pulumi.aws.sqs.Queue;
import com.pulumi.aws.sqs.QueueArgs;

public class MyStack {
        public static void main(String[] args) {
                Pulumi.run(MyStack::stack);
        }

        public static void stack(Context ctx) {
                // Create Input S3 bucket
                var inputBucket = new Bucket("InputBucket", BucketArgs.builder()
                                .bucket("1219474685-in-bucket")
                                .build());

                // Create Output S3 bucket
                var outputBucket = new Bucket("OutputBucket", BucketArgs.builder()
                                .bucket("1219474685-out-bucket")
                                .build());

                // Create Request SQS queue
                var requestQueue = new Queue("RequestQueue", QueueArgs.builder()
                                .name("1219474685-req-queue")
                                .build());

                // Create Response SQS queue
                var responseQueue = new Queue("ResponseQueue", QueueArgs.builder()
                                .name("1219474685-resp-queue")
                                .build());

                // Export the names of the created resources
                ctx.export("inputBucketName", inputBucket.bucket());
                ctx.export("outputBucketName", outputBucket.bucket());
                ctx.export("requestQueueName", requestQueue.name());
                ctx.export("responseQueueName", responseQueue.name());
        }
}
