uniform sampler2D tex;

void main(void)
{
	vec4 value = texture2D(tex, vec2(gl_TexCoord[0]));
	vec4 color = vec4( 0.95, 0.0, 0.0, 1.0);

	if ((value.x >= color.x) && (value.y == color.y) && (value.z == color.z))
		discard;

	gl_FragColor = vec4(value.r, value.g, value.b, 1);
}
